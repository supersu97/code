<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <style type="text/css">
        body {
            font-family: Arial Unicode MS;
            /*background-image: url(BGforPrint.gif);*/
            width:650px;
            height:978px;
        }
        table,table tr th, table tr td { border: 1px solid #000000; }
        table { width: 100%; text-align: center; border-collapse: collapse;}
    </style>
</head>

<body>
<center>
    <strong><span><font size="5">XXXX成绩表</font></span></strong>

    <br/>
    <br/>

    <table>
        <tr>
            <td>姓名</td>
            <td>${stuName}</td>
            <td>学号</td>
            <td>${stuNo}</td>
            <td>年级</td>
            <td>${stuGrade}</td>
        </tr>
        <tr>
            <td>学生类型</td>
            <td>${stuType}</td>
            <td>学院</td>
            <td colspan="3">${stuCollege}</td>
        </tr>
        <tr>
            <td>指导教师</td>
            <td>${stuTeacher}</td>
            <td>专业</td>
            <td colspan="3">${stuMajor}</td>
        </tr>
    </table>
    <br/>
    <table>
        <tr>
            <td colspan="7">成绩表</td>
        </tr>
        <tr>
            <td>课程类别</td>
            <td colspan="3">课程名称</td>
            <td>上课学年/学期</td>
            <td>学分</td>
            <td>成绩</td>
        </tr>

        <#list courseList as course>
            <tr>
                <td>${course.type}</td>
                <td colspan="3">${course.name}</td>
                <td>${course.semester}</td>
                <td>${course.credit}</td>
                <td>${course.achievement}</td>
            </tr>
        </#list>

    </table>

    <img src="test.jpg"/>
</center>
</body>
</html>