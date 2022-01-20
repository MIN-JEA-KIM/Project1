package com.example.trial1;
//새 interface 정의
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService { //                                        endpoint/자원위치(1,2,3 등등 줄수있음. 해보기)
    // @GET( EndPoint-자원위치(URI) )   Ex) https://jsonplaceholder.typicode.com/posts/1
//    @GET,POST,PUT,DELETE다 가능. 무슨 작업을 하고싶은지에 따라
    @GET("news/ncategory/{post}") //@Path = 변수 대입, 중괄호'{}' 안에 감싸진 이름으로 매치(다음줄에 있는 "post"도
    Call<PostResult> getPosts(@Path("post") String post);
//    반환타입 Call<PostResult> - Call은 응답이 왔을때 Callback으로 불려질 타입
//    PostResult - 요청GET에 대한 응답데이터를 받아서 DTO 객체화할 클래스 타입 지정
//    매개변수 '@Path("post") String post' - 매개변수 post가 @Path("post")를 보고 @GET 내부 {post}에 대입
}


//https://jsonplaceholder.typicode.com/  posts/1
//URL=프로토콜+URL                        이게 endpoint = URI
//baseUrl이 되는 부분
