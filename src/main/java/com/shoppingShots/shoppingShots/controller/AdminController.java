package com.shoppingShots.shoppingShots.controller;

import com.shoppingShots.shoppingShots.Utilities.ApplicationConstants;
import com.shoppingShots.shoppingShots.Utilities.CommonUtils;
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

@Controller
@RequestMapping("/admin") //request with /admin will be routed here
public class AdminController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/")
    public String index(){
        return "admin/index";
    }


    @GetMapping("/category")
    public List<Category> category(Model m){
        // loading for all categories
//        m.addAttribute("categories",categoryService.getAllCategory());
        List<Category> categories = categoryService.getAllCategory();
        logger.info(String.valueOf(categories.size()));
        return categories;
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {

        String imageName = file!=null ? file.getOriginalFilename() : "default.jpg";
        category.setImageName(imageName);

        if(categoryService.existCategory(category.getName())){
            session.setAttribute("errorMsg","Category name already exists");
        }
        else{
            Category savedCategory = categoryService.saveCategory(category);
            if(ObjectUtils.isEmpty(savedCategory)){
                session.setAttribute("errorMsg","Not saved! internal server error");
            }
            else{
                // when category is saved then save the image in the static folder
                // TODO should have category id also in the name, or only that should be the name
                try{
                    logger.info("Saving file");
                    File saveFile = new ClassPathResource("public/img").getFile();
                    Path path = Paths.get(saveFile.getAbsoluteFile()+File.separator+"category_img"+File.separator+imageName);
                    logger.info(String.valueOf(path));
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    session.setAttribute("succMsg","Saved successfully");
                }
                catch(Exception e){
                    logger.error("Exception happened");
                }
            }
        }
        return "redirect:/admin/category"; // redirecting to above page
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id,HttpSession session){
        Boolean isDeleted = categoryService.deleteCategory(id);
        if(isDeleted){
            session.setAttribute("succMsg","category delete success");
        }
        else{
            session.setAttribute("errorMsg","something wrong on server");
        }
        return "redirect:/admin/category";
    }
    // TODO might not need in the future
    @GetMapping("/loadEditCategory/{id}")
    public String loadEditCategory(@PathVariable int id,Model m){
        m.addAttribute("category",categoryService.getCategoryById(id));
        return "admin/edit_category";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file,HttpSession session) throws IOException {
        Category oldCategory = categoryService.getCategoryById(category.getId());
        if(!ObjectUtils.isEmpty(oldCategory) && !ObjectUtils.isEmpty(category)){
            oldCategory.setName(category.getName());
            oldCategory.setIsActive(category.getIsActive());
            String newFileName = CommonUtils.checkForImageSave(file, ApplicationConstants.CATEGORY_IMAGE_REF_PATH,oldCategory.getImageName());
            if(newFileName!=null)oldCategory.setImageName(newFileName);
            // Data JPA will saved this over the old category as id is similar
            Category savedCategory = categoryService.saveCategory(oldCategory);
            if(!ObjectUtils.isEmpty(savedCategory)){
                session.setAttribute("succMsg","Updated successfully");
            }
            else{
                session.setAttribute("errorMsg","Error in updating");
            }
        }
        else{
            session.setAttribute("errorMsg","Something is wrong !");
        }
        category = oldCategory;
        return "redirect:/admin/loadEditCategory/"+category.getId();
    }

    @GetMapping("/loadAddProduct")
    public String loadAddProduct(Model m){
        List<Category> categories = categoryService.getAllCategory();
        m.addAttribute("categories",categories);
        return "admin/add_product";
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
