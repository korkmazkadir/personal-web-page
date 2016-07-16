package com.kadirkorkmaz.controller;

import com.kadirkorkmaz.database.entity.ArticleEntity;
import com.kadirkorkmaz.database.entity.MediaEntity;
import com.kadirkorkmaz.database.entity.NavigationEntity;
import com.kadirkorkmaz.form.ArticleForm;
import com.kadirkorkmaz.model.ArticleChanges;
import com.kadirkorkmaz.service.ArticleService;
import com.kadirkorkmaz.service.MediaService;
import com.kadirkorkmaz.service.NavigationService;
import com.kadirkorkmaz.service.UserService;
import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    NavigationService navigationService;
    
    @Autowired
    MediaService mediaService;
    
    
    private static int DEFAULT_PAGE_SIZE = 12;
    

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "Merhaba Kadir");
        List<ArticleEntity> articles = articleService.getAllVisibleArticles();
        model.put("articles", articles);
        return "index";
    }

    @RequestMapping(value = "/yeni-makale", method = RequestMethod.GET)
    public String newArticle(Map<String, Object> model) {
        model.put("", "");
        return "new-article";
    }

    @RequestMapping(value = "/yeni-makale-on-izleme", method = RequestMethod.POST)
    public String newArticlePreview(@ModelAttribute("article") @Valid ArticleForm articleForm, Model model) {
        //PegDownProcessor markdownProcessor = new PegDownProcessor();
        //articleForm.setContent(markdownProcessor.markdownToHtml(articleForm.getContent()));
        articleForm.setContent(articleService.converMarkdowntoHtml(articleForm.getContent()));
        model.addAttribute("article", articleForm);
        return "new-article-preview";
    }

    @RequestMapping(value = "/yeni-makale-yayınla", method = RequestMethod.POST)
    public String newArticlePublish(@ModelAttribute("article") @Valid ArticleForm articleForm, Model model, Principal principal) {

        ArticleEntity article = new ArticleEntity(articleForm.getName(), articleForm.getContent());
        article.setWriter(userService.getUser(principal.getName()));
        articleService.saveArticle(article);

        //PegDownProcessor markdownProcessor = new PegDownProcessor();
        articleForm.setContent(articleService.converMarkdowntoHtml(articleForm.getContent()));

        model.addAttribute("article", articleForm);
        return "new-article-publish";
    }

    @RequestMapping(value = "/say-hello", method = RequestMethod.GET)
    public String sayHello(Map<String, Object> model) {
        model.put("name", "Kadir Korkmaz");
        model.put("message", "Merhaba :)");
        return "say-hello";
    }

    @RequestMapping(value = "/makale/{articleId}/*", method = RequestMethod.GET)
    public String showArticle(@PathVariable long articleId, Map<String, Object> model) {
        ArticleEntity article = articleService.getConvertedArticle(articleId);
        model.put("article", article);
        model.put("articleIdForUsage", articleId);
        return "show-article";
    }

    @RequestMapping(value = "/makale-yonetimi", method = RequestMethod.GET)
    public String manageArticles(Map<String, Object> model) {
        List<ArticleEntity> articles = articleService.getAllActiveArticles();
        model.put("articleList", articles);
        return "article-management";
    }

    @RequestMapping(value = "/makale-duzenle/{articleId}", method = RequestMethod.GET)
    public String editArticle(@PathVariable long articleId, Map<String, Object> model) {
        ArticleEntity article = articleService.getArticle(articleId);
        model.put("article", article);
        return "edit-article";
    }

    @RequestMapping(value = "/makale-degisiklik-kaydet", method = RequestMethod.POST)
    public String saveArticleChanges(@ModelAttribute("article") @Valid ArticleForm articleForm, Model model) {
        ArticleEntity article = articleService.updateArticle(new ArticleEntity(articleForm.getId(), articleForm.getName(), articleForm.getContent()));
        article.setContent(articleService.converMarkdowntoHtml(article.getContent()));
        model.addAttribute("article", article);
        return "show-article";
    }

    @RequestMapping(value = "/makale-siralama-gorunurluk-degistir", method = RequestMethod.POST)
    public @ResponseBody
    boolean saveArticleOrderAndVisibility(@RequestBody ArticleChanges[] changes) {
        articleService.updateOrderAndVisibility(changes);
        return true;
    }

    //FIXME: TEST mamçlı yazıldı. Silinecek.
    @RequestMapping(value = "/show-images", method = RequestMethod.GET)
    public String shoqImages(Map<String, Object> model) {
        String[] images_ = {"/images/kadirkorkmaz.jpg", "/uploaded-images/castle.jpg", "/uploaded-images/bridge.jpg"};
        List<String> images = new LinkedList<>();
        Random generator = new Random();
        for (int i = 1; i < 25; i++) {
            images.add(images_[generator.nextInt(3)]);
        }
        model.put("images", images);
        return "images";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

//        if (error != null) {
//            model.addObject("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            model.addObject("msg", "You've been logged out successfully.");
//        }
        return "login";

    }

    @RequestMapping(value = "/modal", method = RequestMethod.GET)
    public String showModal(Map<String, Object> model) {

        return "image_modal";
    }


    
    
    @RequestMapping(value = "/loadImages", method = RequestMethod.POST)
    public ModelAndView loadImages(@RequestParam(value="pageNumber", required=true ) int currentPageNumber,
            Map<String, Object> model) {
        
        System.out.println("------------------- > loadImages : " + currentPageNumber);

        Page<MediaEntity> imagePage = mediaService.getImages(currentPageNumber - 1, DEFAULT_PAGE_SIZE);
        int[] pageNumbers =  new int[imagePage.getTotalPages()];

        
        for(int i = 0; i < pageNumbers.length;i++){
            pageNumbers[i] = i+1;
        }
        
        List<MediaEntity> images = imagePage.getContent();

        System.out.println("Number of iamges : " + images.size());
        
        model.put("images", images);
        model.put("pageNumbers", pageNumbers);
        model.put("currentPageNumber", currentPageNumber);

        return new ModelAndView("modal_content", model);
    }
    


    @ModelAttribute("navigationBarContentForUser")
    public List<NavigationEntity> getNavBarContentForUser() {
        return navigationService.getAllNavigationContentForUser();
    }

    //@Secured(value={"ADMIN","WRITER"})
    @ModelAttribute("navigationBarContentForAdmin")
    public List<NavigationEntity> getNavBarContentForAdmin() {
        List<GrantedAuthority> authoritys = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authoritys) {
            String authorityString = authority.getAuthority();
            if (authorityString.equals("ADMIN") || authorityString.equals("WRITER")) {
                return navigationService.getAllNavigationContentForAdmin();
            }
        }
        return null;
    }

    @ModelAttribute("articles")
    public List<ArticleEntity> getArticles() {
        return articleService.getAllVisibleArticles();
    }

}
