window.onload = function() {
	/**每过3秒刷新获取session值**/
	setInterval(function() {
		sessionListenner();
	}, 3000)
	function sessionListenner(){
	var sessionPassInfo ='${sessionListener}';
	if(sessionPassInfo=="1"){
		alert("身份信息过期，登陆失效")
		window.redirect()
	}
}
}