$('body').on("click",".expand",function(){
    img = $(this)
    console.log(img.css("transform"))
    if(img.css("transform")=="matrix(6.12323e-17, -1, 1, 6.12323e-17, 0, 0)"||img.css("transform")=="matrix(0, -1, 1, 0, 0, 0)"){
        //收起状态，展开
        img.css("transform","rotate(0deg)")
        img.parent().parent().parent().children(".task-content").css("display","block")
    }else{
        //展开状态，收起
        img.css("transform","rotate(-90deg)")
        img.parent().parent().parent().children(".task-content").css("display","none")
    }
})