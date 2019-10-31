package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MovieCredit {

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
    protected int id;

    @SerializedName("cast")
    protected ArrayList<Cast> casts;

    @SerializedName("crew")
    protected ArrayList<Crew> crews;

    public MovieCredit(int id, ArrayList<Cast> casts, ArrayList<Crew> crews) {
        this.id = id;
        this.casts = casts;
        this.crews = crews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Cast> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<Cast> casts) {
        this.casts = casts;
    }

    public ArrayList<Crew> getCrews() {
        return crews;
    }

    public void setCrews(ArrayList<Crew> crews) {
        this.crews = crews;
    }


    public class Cast {

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

        @SerializedName("cast_id")
        private int cast_id;

        @SerializedName("character")
        private String character;

        @SerializedName("credit_id")
        private String credit_id;

        @SerializedName("gender")
        private int gender;

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("order")
        private int order;

        @SerializedName("profile_path")
        private String profile_path;


        public Cast(int cast_id, String character, String credit_id, int gender, int id, String name, int order, String profile_path) {
            this.cast_id = cast_id;
            this.character = character;
            this.credit_id = credit_id;
            this.gender = gender;
            this.id = id;
            this.name = name;
            this.order = order;
            this.profile_path = profile_path;
        }

        public int getCast_id() {
            return cast_id;
        }

        public void setCast_id(int cast_id) {
            this.cast_id = cast_id;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
    }



    public class Crew {

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

        @SerializedName("credit_id")
        private String credit_id;

        @SerializedName("department")
        private String department;

        @SerializedName("gender")
        private int gender;

        @SerializedName("id")
        private int id;

        @SerializedName("job")
        private String job;

        @SerializedName("name")
        private String name;

        @SerializedName("profile_path")
        private String profile_path;


        public Crew(String credit_id, String department, int gender, int id, String job, String name, String profile_path) {
            this.credit_id = credit_id;
            this.department = department;
            this.gender = gender;
            this.id = id;
            this.job = job;
            this.name = name;
            this.profile_path = profile_path;
        }

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
    }
}
