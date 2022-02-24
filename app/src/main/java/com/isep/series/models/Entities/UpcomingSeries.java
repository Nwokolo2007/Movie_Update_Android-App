package com.isep.series.models.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "UpcomingSeries_List",indices = @Index(value = {"id"},unique = true))
public class UpcomingSeries {
        @PrimaryKey
        @NonNull
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("fullTitle")
        @Expose
        private String fullTitle;
        @SerializedName("year")
        @Expose
        private String year;
        @SerializedName("releaseState")
        @Expose
        private String releaseState;

        @SerializedName("image")
        @Expose
        private String image;


        @NonNull
        public String getId() {
                return id;
        }

        public void setId(@NonNull String id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getFullTitle() {
                return fullTitle;
        }

        public void setFullTitle(String fullTitle) {
                this.fullTitle = fullTitle;
        }

        public String getYear() {
                return year;
        }

        public void setYear(String year) {
                this.year = year;
        }

        public String getReleaseState() {
                return releaseState;
        }

        public void setReleaseState(String releaseState) {
                this.releaseState = releaseState;
        }

        public String getImage() {
                return image;
        }

        public void setImage(String image) {
                this.image = image;
        }
}
