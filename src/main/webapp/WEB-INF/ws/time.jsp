<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>websocket 获得随机数</title>
<script src="<%=request.getContextPath() %>/webjars/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/webjars/sockjs-client/sockjs.min.js"></script>
<script src="<%=request.getContextPath() %>/webjars/stomp-websocket/stomp.min.js"></script>

</head>

<body>
<form>
	<input type="text" name="name" id="name" value="">
	<a href="javascript:void(0)" id="send">发送</a>
	<a href="javascript:void(0)" id="connect">连接</a>
	<a href="javascript:void(0)" id="disconnect">断开</a><br/>
	当前时间(每10秒更新一次):<span id="time"></span>
</form>


</body>

<script type="text/javascript">

var stompClient = null;


function connect() {
    var socket = new SockJS('/myws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/getTime', function (time) {
            showTime(JSON.parse(time.body).value);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/time", {}, JSON.stringify({'name': $("#name").val()}));
}

function showTime(message) {
    $("#time").text(message);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});
</script>
</html>