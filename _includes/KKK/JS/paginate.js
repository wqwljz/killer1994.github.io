"use strict";

var list = document.getElementsByClassName("list-group-item");
var prebtn = document.getElementById("prebtn");
var nxtbtn = document.getElementById("nxtbtn");
function initPage() {
    if(pageNum == 1)
        prebtn.disabled = "disabled";
    else
        prebtn.disabled = "";
    if(pageNum * pageSize > list.length)
        nxtbtn.disabled = "disabled";
    else
        nxtbtn.disabled = "";
    var i;
    for (i of list) {
        i.style.display = "none";
    }
}
function getPage(pagenum, pagesize) {
    initPage();
    var i = (pagenum - 1) * pagesize;
    console.log("pagenum = " + pagenum);
    for (; i < list.length && i < pagesize * pagenum ; i++) {
        list[i].style.display = "";
    }
}
