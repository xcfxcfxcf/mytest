<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My 777777</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
<canvas id="canvas" style="width:700px;height:500px;" ></canvas>	

<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="js/jtopo-0.4.8-min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){                    
    var canvas = document.getElementById('canvas');            
    var stage = new JTopo.Stage(canvas);
    //显示工具栏
    var scene = new JTopo.Scene(stage);
    scene.background = 'aa.png';
    
    var msgNode = new JTopo.TextNode("双击编辑文字, 点击节点可连线(连个节点)");
    msgNode.zIndex++;
    msgNode.setBound(250, 50);
    scene.add(msgNode);
    
    for(var i=0; i<5; i++){
        var node = new JTopo.Node('Node_' + i);
        node.setLocation(Math.random() * 600, Math.random() * 400);
        scene.add(node);        
    }
                                            
    
    var beginNode = null;
    var tempNodeA = new JTopo.Node('tempA');;
    tempNodeA.setSize(1, 1);
    
    var tempNodeZ = new JTopo.Node('tempZ');;
    tempNodeZ.setSize(1, 1);
    
    var link = new JTopo.Link(tempNodeA, tempNodeZ);
    
    scene.mouseup(function(e){
        if(e.button == 2){
            scene.remove(link);
            return;
        }
        if(e.target != null && e.target instanceof JTopo.Node){
            if(beginNode == null){
                beginNode = e.target;
                scene.add(link);
                tempNodeA.setLocation(e.x, e.y);
                tempNodeZ.setLocation(e.x, e.y);
            }else if(beginNode !== e.target){
                var endNode = e.target;
                var l = new JTopo.Link(beginNode, endNode);
                scene.add(l);
                beginNode = null;
                scene.remove(link);
            }else{
                beginNode = null;
            }
        }else{
            scene.remove(link);
        }
    });
    
    scene.mousedown(function(e){
        if(e.target == null || e.target === beginNode || e.target === link){
            scene.remove(link);
        }
    });
    scene.mousemove(function(e){
        tempNodeZ.setLocation(e.x, e.y);
    });
    
    var textfield = $("#jtopo_textfield");
    scene.dbclick(function(event){
        if(event.target == null) return;
        var e = event.target;
        textfield.css({
            top: event.pageY,
            left: event.pageX - e.width/2
        }).show().attr('value', e.text).focus().select();
        e.text = "";
        textfield[0].JTopoNode = e;
    });
    
    $("#jtopo_textfield").blur(function(){
        textfield[0].JTopoNode.text = textfield.hide().val();
    });

});
</script>
</body>
</html>
