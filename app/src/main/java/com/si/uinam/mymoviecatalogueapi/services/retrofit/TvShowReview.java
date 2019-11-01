package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TvShowReview {

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    @SerializedName("id")
    private int id;

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    protected ArrayList<Review> reviews;

    public TvShowReview(int id, int page, ArrayList<Review> reviews) {
        this.id = id;
        this.page = page;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public class Review {

        public String toString() {
            StringBuilder result = new StringBuilder();
            String newLine = System.getProperty("line.separator");

            result.append( this.getClass().getName() );
            result.append( " Object {" );
            result.append(newLine);

            //determine fields declared in this class only (no fields of superclass)
            Field[] fields = this.getClass().getDeclaredFields();

            //print field names paired with their values
            for ( Field field : fields  ) {
                result.append("  ");
                try {
                    result.append( field.getName() );
                    result.append(": ");
                    //requires access to private field:
                    result.append( field.get(this) );
                } catch ( IllegalAccessException ex ) {
                    System.out.println(ex);
                }
                result.append(newLine);
            }
            result.append("}");

            return result.toString();
        }

        @SerializedName("author")
        private String author;

        @SerializedName("content")
        private String content;

        public Review(String author, String content) {
            this.author = author;
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
