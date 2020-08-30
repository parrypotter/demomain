/* 在注册按钮上添加一个单击事件 */
function check(){
        //  json对象
        /*var json = $("#navbar-form-custom").serializeObject();
        var username = JSON.stringify(json);*/
        var username = document.getElementById("top-search").value;
        //将json对象转成json字符串
        $.ajax({
            contentType: "application/json",
            url: "/user/search,
            type: "post",
            cache: false,
            dataType: "json",
            data: JSON.stringify(username),
            success:function(resp) {
                var userList = resp["true"];
                var tdUser = "<td th:text='"+userList.getUid()+"'>"+"</td>"
                +"<td th:text='"+userList.getUsername()+"'>"+"</td>"
                +"<td th:text='"+userList.getUserRole()+"'>"+"</td>"
                +"<td th:text='"+userList.getUserEnable()+"'>"+"</td>";
                $("#userTr").html(tdUser);
                $('#userTr').selectpicker("refresh");
                alert("成功");
            },
            error:function(resp){
                        if(!resp["false"]){
                            alert("出错了");
                        }

                    }

        });

}