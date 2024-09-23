package com.shoppingShots.shoppingShots.controller;

import com.shoppingShots.shoppingShots.Utilities.ApplicationConstants;
import com.shoppingShots.shoppingShots.Utilities.CommonUtils;
import com.shoppingShots.shoppingShots.Utilities.OpResponse;
import com.shoppingShots.shoppingShots.model.Category;
import com.shoppingShots.shoppingShots.service.CategoryService;
import com.shoppingShots.shoppingShots.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categoryService")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    @GetMapping("/activeCategories")
    public List<Category> activeCategory(){
        List<Category> categories = categoryService.getAllActiveCategory();
        return categories;
    }
    @GetMapping("/allCategories")
    public List<Category> allCategory(){
        List<Category> categories = categoryService.getAllCategory();
        return categories;
    }

    @GetMapping("/queryCategory")
    public List<Category> queryCategory(Category category){
        List<Category> categories = categoryService.getCategoryByExample(Example.of(category));
        return categories;
    }

    @PostMapping("/saveCategory")
    public OpResponse saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        OpResponse response = null;

        if(categoryService.existCategory(category.getName())){
            response = new OpResponse ("Category name already exists, try with a different name",400);
        }
        else{
            try{
                String fileName = CommonUtils.checkForImageSave(file,ApplicationConstants.CATEGORY_IMAGE_REF_PATH,"");
                if(fileName!=null){
                    category.setImageName(fileName);
                    Category savedCategory = categoryService.saveCategory(category);
                    if(ObjectUtils.isEmpty(savedCategory)){
                        response = new OpResponse ("Not saved! internal server error",500);
                    }
                    else response = new OpResponse ("Saved successfully",200);
                }
                else {
                    response = new OpResponse ("File could not be saved, please check for request param",400);
                }
            }
            catch(Exception e){
                e.printStackTrace();
                response=new OpResponse (e.getMessage(),500);
            }
        }
        return response;
    }

    @GetMapping("/deleteCategory/{id}")
    public OpResponse deleteCategory(@PathVariable int id,HttpSession session){
        OpResponse OpResponse = null;
        Boolean isDeleted = categoryService.deleteCategory(id);
        if(isDeleted){
            OpResponse = new OpResponse("category delete success",200);
        }
        else{
            OpResponse = new OpResponse("something wrong on server",400);
        }
        logger.info(OpResponse.toString());
        return OpResponse;
    }
    @GetMapping("/getCategory/{id}")
    public Category getCategory(@PathVariable int id,Model m){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/updateCategory")
    public OpResponse updateCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file,HttpSession session) throws IOException {
        OpResponse opResponse = null;
        logger.info(String.valueOf(category.getId()));
        logger.info(String.valueOf(category.getName()));
        Category oldCategory = categoryService.getCategoryById(category.getId());
        if(!ObjectUtils.isEmpty(oldCategory) && !ObjectUtils.isEmpty(category)){
            oldCategory.setName(category.getName());
            oldCategory.setIsActive(category.getIsActive());
            String newFileName = CommonUtils.checkForImageSave(file, ApplicationConstants.CATEGORY_IMAGE_REF_PATH,oldCategory.getImageName());
            if(newFileName!=null)oldCategory.setImageName(newFileName);
            Category savedCategory = categoryService.saveCategory(oldCategory);
            if(!ObjectUtils.isEmpty(savedCategory)){
                opResponse = new OpResponse("Updated successfully",200);
            }
            else{
                opResponse = new OpResponse("Error in updating",400);
            }
        }
        else{
            opResponse = new OpResponse("Something is wrong !",500);
        }
        return opResponse;
    }
}
