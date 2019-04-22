<template>
  <ul class="sub_nav">
    <li><a href="#">홈</a></li>
    <li class="active">
      <router-link to="/">자유게시판</router-link>
      <ul class="depth2_ul">
        <li class="active">
          <router-link to="/sub">서브게시판</router-link>
        </li>
        <li>
          <a href="#">sub2</a>
        </li>
        <li>
          <a href="#">sub3</a>
        </li>
      </ul>
    </li>
    <li><a href="#">방명록</a></li>
  </ul>
</template>

<script>
export default {name: 'Nav'}
</script>
