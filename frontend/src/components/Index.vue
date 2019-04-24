<template>
  <div class="card">
    <div class="card_icon">
      <h3 class="bd_title">자유게시판</h3>
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
        <tr v-for="content in contents">
          <td>{{content.id}}</td>
          <td class="left">{{content.title}}</td>
          <td>작성자</td>
          <td>{{formattedDate(content.createdAt)}}</td>
          <td>0</td>
        </tr>
        <tr v-if="contents.length == 0">
          <td colspan="5" style="text-align: center;">
            데이터가 존재하지 않습니다.
          </td>
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
      <li v-for="n in pages" class="pages" ref="pages">
        <a href="#page" v-on:click="next(n-1)">{{n}}</a>
      </li>
      <li class="dir next">
        <a href="#" title="다음페이지로 이동">»</a>
      </li>
      <li class="dir last">
        <a href="#" title="마지막페이지로 이동">»»</a>
      </li>
    </ul>
    <p class="board_butt">
		  <span class="button">
        <span class="typeA">
          <button type="button" v-on:click="regist();">등록</button>
        </span>
      </span>
    </p>
  </div>
</template>

<script>

/* eslint-disable */
export default {
  name: 'Index',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      contents: [],
      pages: [],
      currentPage: 0,
      totalPage: 1
    }
  },
  // 라이프 사이클 훅에 해당하며 , 해당 인스턴스가 생성된 후 콜백
  created () {
    const page = this.$route.params.page || 0
    this.current = page
    const that = this;
    that.$axios.get('http://localhost:9000/posts?page=' + page)
      .then(function(res) {
        console.dir(res);
        that.contents = res.data.content
        that.currentPage = res.data.number
        that.totalPage = res.data.totalPages

        let arr = []
        for (let i = 0; i < that.totalPage; i++) {
          arr.push(i + 1);
        }
        that.pages = arr
      })
  },
  mounted () {
    // dom이 랜더링된후 콜백
  },
  updated() {
    // data가 변경되어 재 랜더링되었을경우 콜백

    // 현재페이지에 active class add
    let pages = this.$el.querySelectorAll('.pages')
    pages.forEach((el,index) => {
        if(index == this.currentPage) {
          el.classList.add('active')
        } else {
          el.classList.remove('active')
        }

    })
  },
  methods: {
    regist() {
      // $router 와 전역 router는 완전히 동일한 객체이다.
      // $router.push 로 클릭시 라우터간에 이동처리가 가능하다.
      this.$router.push('/regist')
    },
    formattedDate(dateString) {
      var date = new Date(dateString);
      var year = date.getFullYear();                                 //yyyy

      var month = (1 + date.getMonth());                     //M

      month = month >= 10 ? month : '0' + month;     // month 두자리로 저장

      var day = date.getDate();                                        //d

      day = day >= 10 ? day : '0' + day;                            //day 두자리로 저장

      return  year + '-' + month + '-' + day;
    },
    next(index) {
      console.log(index)
      const page = index
      this.current = page
      const that = this;
      that.$axios.get('http://localhost:9000/posts?page=' + page)
        .then(function(res) {
          console.dir(res);
          that.contents = res.data.content
          that.currentPage = res.data.number
          that.totalPage = res.data.totalPages

          let arr = []
          for (let i = 0; i < that.totalPage; i++) {
            arr.push(i + 1);
          }
          that.pages = arr
        })
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
