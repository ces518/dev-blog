<template>
  <div class="card">
    <div class="card_icon">
      <h3 class="bd_title">서브게시판</h3>
    </div>
    <div class="basic_box mt30">
      <div class="inner">
        <form name="frm" action="" method="post">
          <fieldset>
            <legend class="hide">게시물검색</legend>

            <p class="board_total"><span class="bold">총게시물 :</span><span class="bold">4</span> / <span class="bold">페이지 :</span><span class="bold">1/1</span> </p>
            <div class="basic_search">
              <div class="grap">

                <label for="findItem" class="hide">검색조건</label>
                <select name="searchCnd" id="findItem">
                  <option value="" >제목</option>
                  <option value="" >내용</option>
                </select>
              </div>
              <div class="searchinput">
                <label for="findWrite" class="flow">검색어를 입력해주세요.</label>
                <input type="text" name="searchWrd" id="findWrite" value=''  maxlength="35"  />
                <input type="submit" name="submitTy" value="검색" title="검색" />
              </div>
            </div>
          </fieldset>
        </form>
      </div>
    </div>
    <div class="scl_x">
      <table class="tbl_basic center">
        <caption>
          <strong>게시글 목록</strong>
          <details>
            <summary>게시글 목록에 대하여 게시글의 순번과 분류 제목 부서명 등록일 조회를 제공합니다.</summary>
          </details>
        </caption>
        <colgroup>
          <col style="width:8%">

          <col>
          <col style="width:15%">
          <col style="width:13%">
          <col style="width:7%">
        </colgroup>
        <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">등록일</th>
          <th scope="col">조회</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="data in content" v-bind:key="data">
          <td>{{data.id}}</td>
          <td class="left">
            {{data.title}}
          </td>
          <td>
            {{data.contents}}
          </td>
          <td>{{data.createdAt}}</td>
          <td>0</td>
        </tr>
        </tbody>
      </table>
    </div>
    <ul class="paginate">
      <!--페이징 -->
      <li class="dir first">
        <a href="#" title="첫페이지로 이동">««</a>
      </li>
      <li class="dir prev">
        <a href="#" title="이전페이지로 이동">«</a>
      </li>
      <li>
        <a href="#page" title="1페이지, 현재페이지" v-on:click="submit">1</a>
      </li>
      <li class="active"><a href="#page" title="2페이지">2</a>
      </li>
      <li>
        <a href="#page" title="3페이지">3</a>
      </li>
      <li class="dir next">
        <a href="#page" title="다음페이지로 이동">»</a>
      </li>
      <li class="dir last">
        <a href="#page" title="마지막페이지로 이동">»»</a>
      </li>
    </ul>
    <p class="board_butt">
		  <span class="button">
        <span class="typeA">
          <button type="button">등록</button>
        </span>
      </span>
    </p>
  </div>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      content: []
    }
  },
  methods:  {
    submit () {
      let that = this;
      $.ajax({
        url: 'http://localhost:9000/posts',
        dataType:'json',
        method:'GET'
      }).then(function(res) {
        that.content = res.content
      }).catch(function(err) {
         alert(err);
      });
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
