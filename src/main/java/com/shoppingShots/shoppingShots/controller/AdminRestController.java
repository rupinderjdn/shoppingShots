package com.shoppingShots.shoppingShots.controller;

import com.shoppingShots.shoppingShots.Utilities.ApplicationConstants;
import com.shoppingShots.shoppingShots.Utilities.CommonUtils;
import com.shoppingShots.shoppingShots.Utilities.OpResponse;
import com.shoppingShots.shoppingShots.model.Category;
import com.shoppingShots.shoppingShots.model.Product;
import com.shoppingShots.shoppingShots.service.CategoryService;
import com.shoppingShots.shoppingShots.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/adminRest") //request with /admin will be routed here
public class AdminRestController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

//    @GetMapping("/")
//    public String index(){
//        return "admin/index";
//    }


    @GetMapping("/category")
    public List<Category> category(){

        //        List<Category> categories = categoryService.getAllCategory();

        Category category = new Category();
        category.setName("restTest3");
        List<Category> categories = categoryService.getCategoryByExample(Example.of(category));

        //        List<Category> categories = categoryService.getAllActiveCategory();
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
            session.setAttribute("errorMsg","Category name already exists");
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

    @PostMapping("/saveProduct")
    public OpResponse saveProduct(@ModelAttribute Product product,@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        OpResponse opResponse = null;
        if(!ObjectUtils.isEmpty(product)){
            String fileName = CommonUtils.checkForImageSave(file,ApplicationConstants.PRODUCT_IMAGE_REF_PATH,"");
            if(fileName !=null){
                product.setImage(fileName);
                Product savedProduct = productService.saveProduct(product);
                opResponse = new OpResponse("Product saved successfully "+savedProduct.getTitle(),200);
            }
            else opResponse = new OpResponse("Error in file saving",400);
        }
        else{
            opResponse = new OpResponse("Something wrong on the server!",500);
        }
        return opResponse;
    }

    @GetMapping("/products")
    public List<Product> loadViewProduct(Model m){
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("deleteProduct/{id}")
    public OpResponse deleteProduct(@PathVariable int id,HttpSession session){
        OpResponse opResponse = null;
        Boolean deleteProduct = productService.deleteProduct(id);
        if(deleteProduct){
            opResponse = new OpResponse("Product deleted successfully",200);
        }
        else{
            opResponse = new OpResponse("Something wrong on the server",400);
        }
        return opResponse;
    }

    @PostMapping("/updateProduct")
    public OpResponse editProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file,HttpSession session,Model m) throws IOException {
        OpResponse opResponse = null;
        Product oldProduct = productService.getProductById(product.getId());
        if(!ObjectUtils.isEmpty(oldProduct) && !ObjectUtils.isEmpty(product)){
            oldProduct.setTitle(product.getTitle());
            oldProduct.setCategory(product.getCategory());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setDiscount(product.getDiscount());
            oldProduct.setDiscountPrice(product.getDiscountPrice());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setStock(product.getStock());
            oldProduct.setActive(product.isActive());
            String newFileName = CommonUtils.checkForImageSave(file, ApplicationConstants.PRODUCT_IMAGE_REF_PATH,oldProduct.getImage());
            if(newFileName!=null)oldProduct.setImage(newFileName);
            logger.info(oldProduct.getImage() + " ==> "+newFileName);
            // Data JPA will save this over the old product as id is similar
            Product savedProduct = productService.saveProduct(oldProduct);
            if(!ObjectUtils.isEmpty(savedProduct)){
                session.setAttribute("succMsg","Updated successfully");
                opResponse = new OpResponse("Updated successfully",200);
            }
            else{
                session.setAttribute("errorMsg","Error in updating");
                opResponse = new OpResponse("Error in updating",400);
            }
        }
        else{
            session.setAttribute("errorMsg","Something is wrong !");
            opResponse = new OpResponse("Something is wrong !",500);
        }
        return opResponse;
    }

    @GetMapping("/queryProduct")
    public List<Product> queryProduct(Product product){
        List<Product> products = productService.getProductByExample(Example.of(product));
        return products;
    }
}
