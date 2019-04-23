<template>
  <div class="card">
    <div class="card_icon">
      <h3 class="bd_title">자유게시판</h3>
    </div>
    <form method="post" action="">
      <div class="scl_x mt30">
        <table class="tbl_basic tbl_form center">
          <caption>
            <strong>게시글 등록</strong>
            <details>
              <summary>게시글 등록</summary>
            </details>
          </caption>
          <colgroup>
            <col style="width:25%;">
            <col>
          </colgroup>
          <tbody>
          <tr>
            <th scope="col">제목</th>
            <td class="left">
              <div class="form_group">
                <input type="text" name="title" value="">
                <span class="focus_border"></span>
              </div>
            </td>
          </tr>
          <tr>
            <th scope="col">내용</th>
            <td class="left">
              <textarea name="content" class="bd_cont" ></textarea>
            </td>
          </tr>
          <tr>
            <th scope="col">작성자</th>
            <td class="left">
              <div class="form_group">
                <input type="text" name="writer" value="">
                <span class="focus_border"></span>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </form>
    <p class="board_butt">
					<span class="button">
						<span class="typeA">
							<button type="button" v-on:click="list()">목록</button>
						</span>
						<span class="typeA">
							<button type="button" v-on:click="save()">저장</button>
						</span>
						<span class="typeA">
							<button type="button" v-on:click="reset()">취소</button>
						</span>
					</span>
    </p>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: 'regist',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    list () {
      this.$router.push('/')
    },
    save () {
      const self = this;

    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
