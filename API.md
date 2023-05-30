# 接口文档

|      接口      |                   请求体                   |                    响应体                    |     权限      |
| :------------: | :----------------------------------------: | :------------------------------------------: | :-----------: |
|  /user/login   |        Request {username, password}        |  Response {token} / ErrorResponse {message}  |       /       |
| /user/register | Request {username, password, email, phone} | Response {message} / ErrorResponse {message} |       /       |
|   /user/list   |                     /                      |                 Response {}                  | 登录（token） |
|  /user/images  |                     /                      |                                              | 登录（token） |
|  /user/worlds  |                     /                      |                                              | 登录（token） |

统一的失败响应体：

```json
{
    message: ""
}
```

### 1、登录

URL：/user/login

请求方法：Post

权限：/

请求体：

```json
{
    username: "",
    password: "",
}
```

响应体：

```json
{
    token: ""
}
```

### 2、注册

URL：/user/register

请求方法：Post

权限：/

请求体：

```json
{
    username: "",
    password: "",
    email: "",
    phone: ""
}
```

响应体：

```json
{
    message: ""
}
```

### 3、个人信息

URL：/user/profile

请求方法：Get

权限：login

请求体为空

响应体：

```json
{
    "username": "",
    "email": "",
    "phone": ""
}
```

### 4、修改密码

URL：/user/chpwd

请求方法：Post

权限：login

请求体：

```json
{
    "oldpassword": "",
    "newpassword": ""
}
```

响应体：

```json
{
    message: ""
}
```

### 5、形象选择历史

URL：/user/images

请求方法：Get

权限：login

请求体为空

响应体：

```json
{
    "infos": [
        {
            "image": "",
            "index": 1
        },
        ...
    ]
}
```

### 6、添加一次形象选择记录

URL：/user/addImage

请求方法：Post

权限：login

请求体：

```json
{
    "name": ""
}
```

响应体：

```json
{
    "message": ""
}
```

### 7、世界选择历史

URL：/user/worlds

请求方法：Get

权限：login

请求体为空

响应体：

```json
{
    "infos": [
        {
            "world": "",
            "index": 1
        },
        ...
    ]
}
```

### 8、添加一次世界选择历史

URL：/user/addWorld

请求方法：Post

权限：login

请求体：

```json
{
    "name": ""
}
```

响应体：

```json
{
    "message": ""
}
```