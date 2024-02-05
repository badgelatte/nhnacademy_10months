# commons-upload를 이용한 File Upload

## Dependency

* pom.xml

```xml
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.3</version>
</dependency>
```

## MultipartResolver

CommonsMultipartResolver

## Demo

```
git checkout upload2
```

## Test

### FileUploadController 경로변경

```java
private static final String UPLOAD_DIR = "/Users/user/Downloads/";
```

* upload 경로 변경하기 : project root/upload directory 만들어서 경로지정하기 ( 경로는 본인의 프로젝트 경로에 맞게 설정)

```java
private static finalUPLOAD_DIR = /Users/visualp/IdeaProjects/academy-spring-mvc/upload/
```

* [http://localhost:8080/upload](http://localhost:8080/upload)


### servlet 3.0 이상부터는 fileupload api가 제공.. Servlet 3.0 api를 사용해요\~