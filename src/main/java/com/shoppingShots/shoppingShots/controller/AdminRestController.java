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
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

//    @GetMapping("/")
//    public String index(){
//        return "admin/index";
//    }


    @GetMapping("/category")
    public List<Category> category(Model m){
        List<Category> categories = categoryService.getAllCategory();
        logger.info(String.valueOf(categories.size()));
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
    public String saveProduct(@ModelAttribute Product product,@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        String fileName = file!=null ? file.getOriginalFilename() : "default.jpg";
        product.setImage(fileName);
        Product savedProduct = productService.saveProduct(product);
        if(!ObjectUtils.isEmpty(savedProduct)){
            // TODO need a common method to save the image
            logger.info("Saving file");
            File saveFile = new ClassPathResource("public/img").getFile();
            Path path = Paths.get(saveFile.getAbsoluteFile()+File.separator+"product_img"+File.separator+product.getImage());
            logger.info(String.valueOf(path));
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            session.setAttribute("succMsg","Saved successfully");
        }
        else{
            session.setAttribute("errorMsg","Something wrong on the server!");
        }
        return "redirect:/admin/loadAddProduct";
    }

    @GetMapping("/products")
    public String loadViewProduct(Model m){
        List<Product> products = productService.getAllProducts();
        m.addAttribute("products",products);
        return "admin/products";
    }

    @GetMapping("deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id,HttpSession session){
        Boolean deleteProduct = productService.deleteProduct(id);
        if(deleteProduct){
            session.setAttribute("succMsg","Product deleted successfully");
        }
        else{
            session.setAttribute("errorMsg","Something wrong on the server");
        }
        return "redirect:admin/products";
    }
    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable int id,Model m) throws IOException {
        m.addAttribute("product",productService.getProductById(id));
        m.addAttribute("categories",categoryService.getAllCategory());
        return "admin/edit_product";
    }

    @PostMapping("/updateProduct")
    public String editProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file,HttpSession session,Model m) throws IOException {
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
            }
            else{
                session.setAttribute("errorMsg","Error in updating");
            }
        }
        else{
            session.setAttribute("errorMsg","Something is wrong !");
        }
        product = oldProduct;
        return "redirect:/admin/editProduct/"+product.getId();
    }

}
