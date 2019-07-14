 package com.java.utils;
 
 import java.util.List;
 
 public class Pagination
 {
   private List<?> rows;
   private long total;
   private List<?> footer;
 
   public Pagination()
   {
   }
 
   public Pagination(List<?> rows, long total)
   {
     this.rows = rows;
     this.total = total;
   }
 
   public Pagination(List<?> rows, long total, List<?> footer) {
     this.rows = rows;
     this.total = total;
     this.footer = footer;
   }
 
   public List<?> getRows() {
     return this.rows;
   }
 
   public void setRows(List<?> rows) {
     this.rows = rows;
   }
 
   public long getTotal() {
     return this.total;
   }
 
   public void setTotal(long total) {
     this.total = total;
   }
 
   public List<?> getFooter() {
     return this.footer;
   }
 
   public void setFooter(List<?> footer) {
     this.footer = footer;
   }
 }

