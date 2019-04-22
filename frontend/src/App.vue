<template>
  <div id="app">
    <Header/>
    <div class="wrapper">
        <Nav/>
      <div class="content">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>/* eslint-disable */
import Header from "./components/layout/Header"
import Nav from "./components/layout/Nav"
export default {
  name: 'App',
  components: {Nav, Header}
}
</script>
