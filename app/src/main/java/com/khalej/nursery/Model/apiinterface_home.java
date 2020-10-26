package com.khalej.nursery.Model;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface apiinterface_home {

    @FormUrlEncoded
    @POST("nursery/Nursery_login.php")
    Call<List<contact_userinfo>> getcontacts_login(@Field("phonee") String phone, @Field("passw") String password, @Field("usertype") int user);
    @FormUrlEncoded
    @POST("nursery/Nursery_Registraion.php")
    Call<ResponseBody> getcontacts_newaccount(@Field("name") String name, @Field("password") String password, @Field("address") String address,
                                              @Field("phone") String phone, @Field("usertype") int user,@Field("type") int type);

    @GET("nursery/Nursery_annonce.php")
    Call<List<contact_annonce>> getcontacts_annonce();

    @FormUrlEncoded
    @POST("nursery/Nursery_first_category.php")
    Call<List<contact_home>> getcontacts_second(@Field("id") int id);

    @GET("nursery/Nursery_category.php")
    Call<List<contact_home>> getcontacts_first();
    @GET("nursery/Nursery_Allfirst_category.php")
    Call<List<contact_home>> getcontacts_all_first();
    @FormUrlEncoded
    @POST("nursery/Nursery_add_order.php")
    Call<ResponseBody> getcontacts_order(@Field("name") String name,@Field("phone") String phone,@Field("address") String address,
                                         @Field("sud_id") int sud_id,@Field("charge") double charge
    );

    @FormUrlEncoded
    @POST("nursery/Nursery_add_category.php")
    Call<ResponseBody> getcontacts_add_first_category(@Field("name") String name, @Field("image") String image,
                                                      @Field("price") String price,@Field("description")String description,
                                                      @Field("error") String error ,@Field("id")int id);
    @FormUrlEncoded
    @POST("nursery/Nursery_update_category.php")
    Call<ResponseBody> getcontacts_update_first_category(@Field("name") String name, @Field("image") String image,
                                                      @Field("price") String price,@Field("description")String description,
                                                      @Field("error") String error ,@Field("id")int id ,@Field("a_id")int a_id);
    @FormUrlEncoded
    @POST("nursery/Nursery_add_general_category.php")
    Call<ResponseBody> getcontacts_add_general_category(@Field("name") String name, @Field("image") String image);

    @FormUrlEncoded
    @POST("nursery/Nursery_update_general_category.php")
    Call<ResponseBody> getcontacts_update_general_category(@Field("name") String name, @Field("image") String image ,@Field("id")int id);

    @FormUrlEncoded
    @POST("nursery/Nursery_delete_category.php")
    Call<ResponseBody> delete_first(@Field("id") int id);
    @FormUrlEncoded
    @POST("nursery/Nursery_delete_general_category.php")
    Call<ResponseBody> delete_general(@Field("id") int id);
    @FormUrlEncoded
    @POST("nursery/Nursery_add_annonce.php") Call<ResponseBody> getcontacts_add_annonce(@Field("image") String image);
    @FormUrlEncoded
    @POST("nursery/Nursery_update_annonce.php") Call<ResponseBody> getcontacts_update_annonce(@Field("image") String image,
                                                                                              @Field("id") int id);

    @FormUrlEncoded
    @POST("nursery/Nursery_delete_annonce.php")
    Call<ResponseBody> delete_annonce(@Field("id") int id);
    @FormUrlEncoded
    @POST("nursery/Nursery_update_point.php")
    Call<ResponseBody> chargepoints(@Field("id") int id,@Field("points") int  points);
    @FormUrlEncoded
    @POST("nursery/Nursery_delete_order.php")
    Call<ResponseBody> delete_order(@Field("id") int id);

    @GET("nursery/Nursery_all_neworders.php")
    Call<List<contact_order>> get_all_neworders();
    @GET("nursery/Nursery_all_users.php")
    Call<List<contact_userinfo>> get_all_users();
    @FormUrlEncoded
    @POST("nursery/Nursery_get_point.php")
    Call<ResponseBody> getpoints(@Field("id") int id,@Field("points") int  points);

    @FormUrlEncoded
    @POST("nursery/Nursery_new_absence.php")
    Call<ResponseBody> getcontacts_add_absense(@Field("id") int id, @Field("name") String name, @Field("details") String details,
                                              @Field("month") int month, @Field("num_days") int num_days);
    @FormUrlEncoded
    @POST("nursery/Nursery_new_report.php")
    Call<ResponseBody> getcontacts_add_report(@Field("id") int id,@Field("details") String details,
                                               @Field("month") int month);


    @FormUrlEncoded
    @POST("nursery/Nursery_get_absence.php")
    Call<List<contact_absence>> getcontacts_absence(@Field("id") int id);

    @FormUrlEncoded
    @POST("nursery/Nursery_get_report.php")
    Call<List<contact_report>> getcontacts_report(@Field("id") int id);

    @FormUrlEncoded
    @POST("nursery/Nursery_get_chat.php")
    Call<List<contact_chat>> getcontacts_chat(@Field("id") int id);

    @FormUrlEncoded
    @POST("nursery/Nursery_add_chat.php")
    Call<ResponseBody> getcontacts_addchat(@Field("sub_id") int id,@Field("sender") int sender,@Field("text") String text);

    @GET("nursery/Nursery_all_users_nursary.php")
    Call<List<contact_userinfo>> get_all_users_nursary();

}

