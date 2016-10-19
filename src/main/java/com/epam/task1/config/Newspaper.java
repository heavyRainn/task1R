package com.epam.task1.config;

import com.epam.task1.model.Author;
import com.epam.task1.model.Tag;
import com.epam.task1.model.Theme;
import com.epam.task1.service.AuthorService;
import com.epam.task1.service.NewsService;
import com.epam.task1.service.TagService;
import com.epam.task1.service.UserService;
import com.epam.task1.service.search.NewsSearchCriteria;
import com.epam.task1.service.search.NewsSearchType;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Newspaper {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        ApplicationContext ctx = new AnnotationConfigApplicationContext(NewspaperConfig.class);

        AuthorService authorService = ctx.getBean(AuthorService.class);
        UserService userService = ctx.getBean(UserService.class);
        TagService tagService = ctx.getBean(TagService.class);
        NewsService newsService = ctx.getBean(NewsService.class);

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("#HOME"));
        tags.add(new Tag("#SELFIE"));
        NewsSearchCriteria nsc = new NewsSearchCriteria(NewsSearchType.BY_TAGS);
        nsc.setTags(tags);

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Zigmund", "Freid"));

        System.out.println("UserService : " + userService.authenticate("Grek221", "1222"));
        System.out.println("AuthorService : " + authorService.read());
        System.out.println("AuthorService : " + authorService.read(501));
        System.out.println("TagService : " + tagService.read());
        System.out.println("TagService : " + tagService.read(501));
        System.out.println("NewsService : " + newsService.viewAllNews());
        System.out.println("NewsService : " + newsService.viewAllNews(Theme.FASHION));
        System.out.println("NewsService : " + newsService.totalCount());
        System.out.println("NewsService : " + newsService.totalCount(Theme.FASHION));
        System.out.println("NewsService : " + newsService.viewAllPopularNews());
        System.out.println("NewsService BY TAGS: " + newsService.viewASingleNews(nsc));

        NewsSearchCriteria nsc1 = new NewsSearchCriteria(NewsSearchType.BY_AUTHOR);
        nsc1.setAuthors(authors);

        System.out.println("NewsService BY AUTHORS: " + newsService.viewASingleNews(nsc1));

    }
}
