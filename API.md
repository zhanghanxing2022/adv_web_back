# 接口文档

### 0.1 统一的失败请求

响应码：400，在angular前端可以通过如下代码处理：

```typescript
this.http.post(url, body).subscribe(
  	(response : any) => {
		// 请求成功的回调
  	}, 
	(response : any) => {
        // 请求失败的回调，即后端返回400响应码
    	window.alert(response.error);
  	}
)
```

### 0.2 用户权限处理

后端采用token机制处理用户权限，前端在发送需要用到用户身份的请求时，只需要在请求头中添加`token = ...`即可，如果没有token不要加（而不是加一个`token = null`），其余不需要任何有关的用户信息，包括`userID`等。

另外，如果前端的一次请求中，请求头携带了`token`字段，那么后端会在响应头中同样设置`token = ...`用来更新token延长其时限，前端可以从`response.header`中拿到并保存。

### 1、登录

URL：/user/login

请求方法：Post

权限：/

请求体：

```json
{
    email: "",
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

（目前看前端并没有处理phone，所以这里的phone不用填也没事，后端没问题）

响应体：

```json
{
    message: ""
}
```

（这个message其实没什么用，可以忽略，只要返回码是200不是`BadRequest`就是正确）

### 3、个人信息

URL：/user/profile

请求方法：Get

权限：login

请求体为空（请求头带token）

响应体：

```json
{
    "userID": 0,
    "username": "",
    "email": "",
    "phone": "",
    "figure": "",
    "skin": "",
    "algorithm": "",
    "learn": "",
    "practice": ""
}
```

其中`figure`为用户使用次数最多的角色，`skin`为用户使用次数最多的皮肤；`algorithm`为学习+练习总数最多的算法，`learn`为学习次数最多的算法，`practice`为练习次数最多的算法。

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

### 5、形象选择历史记录

URL：/user/figures

请求方法：Get

权限：login

请求体为空

响应体：

```json
{
    "figures": [
        {
            "userID": 8,
            "figure": "",
            "skin": "",
            "order": 1,
            "time": ""
        },
        {
            ...
        },
    ]
}
```

其中`figures`数组的每个item是一条记录，`figure`为此次选择的形象，`skin`为此次选择的皮肤，`order`为这条记录的次序，`time`为该记录的时间。

### 6、用户最喜欢的形象和皮肤

URL：/user/favoriteFigureAndSkin

请求方法：Get

权限：login

请求体为空

响应体：

```json
{
    "figure": "",
    "skin": ""
}
```

其中`figure`为形象，`skin`为皮肤。

### 7、添加一次形象选择记录

URL：/user/addFigure

请求方法：Post

权限：login

请求体：

```json
{
    "figure": "",
    "skin": ""
}
```

响应体：

```json
{
    "message": ""
}
```

和register一样，如果响应没有报400就可以，正确响应的message可以不用管。

### 8、算法相关历史记录

URL：/user/algorithms

请求方法：Get

权限：login

请求体为空

响应体：

```json
{
    "userAlgorithmList": [
        {
            "userID": 8,
            "algorithm": "",
            "type": "",
            "time": ""
        },
        {
            ...
        }
    ]
}
```

其中`userAlgorithmList`数组的每个item是一条记录，`algorithm`为此次的算法，如`MergeSort`、`QuickSort`；`type`为此次选择的类型，包括`LEARN`和`PRACTICE`两种；`time`为该记录的时间。

### 9、算法相关历史统计信息

URL：/user/algorithmInfo

请求方法：Get

权限：login

请求体为空

响应体：

```json
{
    "infos": [
        {
            "userID": 8,
            "algorithm": "HeapSort",
            "learn": 1,
            "practice": 1
        },
        {
            "userID": 8,
            "algorithm": "MergeSort",
            "learn": 1,
            "practice": 1
        },
        {
            "userID": 8,
            "algorithm": "QuickSort",
            "learn": 2,
            "practice": 1
        }
    ]
}
```

其中`infos`数组的每一个item是一种算法的统计信息，`learn`为学习次数，`practice`为练习次数。

### 10、用户最喜欢的算法

URL：/user/favoriteAlgorithm

请求方法：Get

请求权限：login

请求体为空

响应体：

```json
{
    "total": "",
    "learn": "",
    "practice": ""
}
```

其中total为学习+练习总数最多的算法，learn为学习次数最多的算法，practice为练习次数最多的算法。

### 11、添加一条算法历史记录

URL：/user/addAlgorithm

请求方法：Post

请求权限：login

请求体：

```json
{
    "algorithm": "",
    "type": ""
}
```

algorithm为已有的算法，type为此条记录的类型：LEARN或PRACTICE

响应体：

```json
{
    "message": ""
}
```

和register一样，如果响应没有报400就可以，正确响应的message可以不用管。