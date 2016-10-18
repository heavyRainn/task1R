package com.epam.task1.config;

import com.epam.task1.model.Theme;
import com.epam.task1.service.AuthorService;
import com.epam.task1.service.NewsService;
import com.epam.task1.service.TagService;
import com.epam.task1.service.UserService;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Newspaper {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        ApplicationContext ctx = new AnnotationConfigApplicationContext(NewspaperConfig.class);

        AuthorService authorService = ctx.getBean(AuthorService.class);
        UserService userService = ctx.getBean(UserService.class);
        TagService tagService = ctx.getBean(TagService.class);
        NewsService newsService = ctx.getBean(NewsService.class);

        System.out.println("UserService : " + userService.authenticate("Grek221", "1222"));
        System.out.println("AuthorService : " + authorService.read());
        System.out.println("AuthorService  : " + authorService.read(501));
        System.out.println("TagService : " + tagService.read());
        System.out.println("TagService : " + tagService.read(501));
        System.out.println("NewsService : " + newsService.viewAllNews());
        System.out.println("NewsService : " + newsService.viewAllNews(Theme.FASHION));
        System.out.println("NewsService : " + newsService.totalCount());
        System.out.println("NewsService : " + newsService.totalCount(Theme.FASHION));
        System.out.println("NewsService : " + newsService.viewAllPopularNews());

    }
}
