<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${contextPath}/css/index.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
var clickedObj;
	$(document).ready(function(){
		$("#three input[type='text']").keyup(function(){debugger;
			var className = $(this).prop("class");
			$(clickedObj).find(className).html($(this).val());
		});
		
		$("#three input[type !='text']").click(function(){debugger;
			
		});
		
		
		$(".items").draggable({
			cancel: "a.ui-icon", // clicking an icon won't initiate dragging
	        //revert: "invalid", // when not dropped, the item will revert back to its initial position
	        revert: true, // bounce back when dropped
	        helper: "clone", // create "copy" with original properties, but not a true clone
	        cursor: "move"
	        , revertDuration: 0 
		});
		$("#one").droppable({
			accept: "#two .items",
	        activeClass: "ui-state-highlight",
	        drop: function (event, ui) {
	            $(this)
	                .append(ui.helper.clone(false).css({
	                position: 'relative',
	                left: '0px',
	                top: '0px'
	            }));
	        }
		});
	});
	
	function clicked(obj){
		clickedObj = obj;
		var element = $(obj).find('textarea,input');
		var elementType = $(element).prop("nodeName");
		
		switch(elementType){
			case "INPUT":
				var tpeOfInputElement = $(element).prop("type");
				$("#two").hide();
				$("#three").show();
				if(tpeOfInputElement == "text"){
					$("#three .number").hide();
				}else if(tpeOfInputElement == "number"){
					$("#three .number").show();
				}
		}
	}
	
	function backToTwo(){
		$("#two").show();
		$("#three").hide();
	}
</script>

<div class="wrapper">
    <div id="one"></div>
    <div id="two">
    	<div class='items' onclick="clicked(this);">
    		<label>label #</label>
    		<input type="text" readonly="readonly" placeholder="Text Box">
    	</div>
    	<div class='items' onclick="clicked(this);">
    		<label>label #</label>
    		<input type="number" readonly="readonly" placeholder="Text Box">
    	</div>
    	<div class='items' onclick="clicked(this);">
    		<label>label #</label>
    		<textarea rows="5" cols="7" readonly="readonly"></textarea>
    	</div>
    	
    	
    </div>
    <div id="three" style="display: none;"><a onclick="backToTwo();" href="#">Back</a>
    	Label: <input type="text" class='label' placeholder="Type Label">
    	Required: <input type="checkbox" class='required'>
    	Disabled: <input type="checkbox" class='disabled'>
    	Read Only: <input type="checkbox" class='readonly'>
    	
    	<div class="number" style="display: none;">
    		Min: <input type="number" class='min'>
    		Max: <input type="number" class='max'>
    	</div>
    </div>
</div>
