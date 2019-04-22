<template>
  <div class="header" id="header">
    <h1 class="logo">
      <a href="/"><span>B</span>STORY</a>
    </h1>
    <div class="nav">
      <ul>
        <li><a href="login.html">로그인</a></li>
        <li><a href="join.html">회원가입</a></li>
      </ul>
    </div>
    <a href="#" class="sitemap" id="mobile_menu_btn">sitemap</a>
    <div id="mobile_menu">
      <div class="mobile_menu_head">
        <a href="login.html" class="mobile_logo">
          로그인
        </a>
        <a href="join.html" class="mobile_logo2">
          회원가입
        </a>
      </div>
      <ul id="gnb_device">
        <li class="th1 n1 arcodian">
          <a href="#" class="th1_lnk">
            <span>홈</span>
          </a>
        </li>
        <li class="th1 n1 arcodian">
          <a href="#" class="th1_lnk">
            <span>자유게시판</span>
          </a>
          <ul class="depth2_ul summry">
            <li>
              <a href="#">sub1</a>
            </li>
            <li>
              <a href="#">sub2</a>
            </li>
            <li>
              <a href="#">sub3</a>
            </li>
          </ul>
        </li>
        <li class="th1 n1 arcodian">
          <a href="#" class="th1_lnk">
            <span>방명록</span>
          </a>
        </li>
      </ul>
      <button type="button" id="mobile_menu_close">
        <span class="skip">닫기</span>
      </button>
    </div>
  </div>
</template>
<script>
/* eslint-disable*/
global.jQuery = require('jquery');
var $ = global.jQuery;
window.$ = $;

export default {
  name: 'Header'
}

var _topmenu = new topmenu('#mobile_menu','')

function topmenu(el, menuCode, duration){
  var self = this;
  self.el = $(el); // 탑메뉴 최상위 엘리먼트
  self.is_open = false;
  self.is_start = false;

  // menu code parsing
  self.depth1 = Number(menuCode.substr(0,2))-1;
  self.depth2 = Number(menuCode.substr(2,2))-1;
  self.depth3 = Number(menuCode.substr(4,2))-1;

  // duration
  self.duration = duration ? duration : 300;

  // depth1
  self.depth1_li = self.el.find('#gnb_device').children('li');

  // depth2
  self.depth2_li = self.el.find('.depth2_ul').children('li');

  // depth3
  self.depth3_li = self.el.find('.depth3_ul').children('li');


  // current
  self.current_dpeth1_li = self.el.find('#gnb_device').children('li').eq(self.depth1);
  self.current_dpeth2_li = self.current_dpeth1_li.find('.depth2_ul').children('li').eq(self.depth2);
  self.current_dpeth3_li = self.current_dpeth2_li.find('.depth3_ul').children('li').eq(self.depth3);


  // set current
  self.current = function() {
    self.current_dpeth1_li.addClass('current');
    self.current_dpeth2_li.addClass('current');
    self.current_dpeth3_li.addClass('current');
  }


  // set icon
  self.depth1_li.each(function() {
    var jqThis = $(this);
    if ( jqThis.find('.depth2_ul').length > 0 ) {
      jqThis.addClass('arcodian');
      jqThis.children('a').on('click', function(e){
        var jqThis_2 = $(this);
        e.preventDefault();
        self.depth1_li.removeClass('active');
        jqThis_2.parent().addClass('active');
        self.depth1_li.find('.summry').stop().slideUp(self.duration);
        jqThis.find('.summry').stop().delay(self.duration).slideDown(self.duration);
      });
    }
  });

  self.depth2_li.each(function() {
    var jqThis = $(this);
    if ( jqThis.find('.depth3_ul').length > 0 ) {
      jqThis.addClass('arcodian');
      jqThis.children('a').on('click', function(e){
        var jqThis_2 = $(this);
        e.preventDefault();
        self.depth2_li.removeClass('active');
        jqThis_2.parent().addClass('active');
        self.depth2_li.find('.depth3_ul').stop().slideUp(self.duration);
        jqThis.find('.depth3_ul').stop().delay(self.duration).slideDown(self.duration);
      });
    }
  });

  // menu open
  self.open = function() {
    self.el.css({
      zIndex: ''
    }).animate({
      right:'0px'
    },self.duration);

    self.bg_el.stop().fadeIn();
  }


  // menu close
  self.close = function() {
    self.el.animate({
      right:'-300px'
    },( self.is_start ? self.duration : 0), function(){
      self.el.css({
        zIndex : -1
      })
    }); // 처음 로딩때는 duration을 0으로

    self.bg_el.stop().fadeOut();
  }


  // event
  $('#mobile_menu_btn').on('click', function(){
    self.open();
  });
  $('#mobile_menu_close').on('click', function(){
    self.close();
  });


  // menu bg
  var bg = '<div id="mobile_menu_bg"></div>';
  self.bg_el = self.el.before(bg).prev();
  self.bg_el.css({display:'none'});

  self.bg_el.on('click', function(){
    self.close();
  });


  // start
  // self.current();
  self.close();
  self.is_start = true;

}
</script>
