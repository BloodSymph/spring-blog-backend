package com.company.blog.utils;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class SlugGenerator {
    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final int LIMIT = 15;

    public static String toSlug(String input){
        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("-");
        String normalize = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String noLatin = NON_LATIN.matcher(normalize).replaceAll("");
        String slug = noLatin.length() > LIMIT ? noLatin.substring(0, LIMIT) : noLatin;
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
