import { createRouter, createWebHistory } from "vue-router";
import EntrancePage from "../views/EntrancePage.vue";
import Login from "../views/Login.vue";
import Signup from "../views/Signup.vue";
import MainPage from "../views/MainPage.vue";
import MyTeam from "../views/MyTeam.vue";
import TeamDetail from "@/components/MyTeam/TeamDetail.vue";
import MyPage from "../views/MyPage.vue";

const routes = [
  {
    path: "/",
    name: "EntrancePage",
    component: EntrancePage,
  },
  {
    path: "/main",
    name: "MainPage",
    component: MainPage,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
  },
  {
    path: "/myteam",
    name: "MyTeam",
    component: MyTeam,
  },
  {
    path: "/myteam/:id",
    name: "TeamDetail",
    component: TeamDetail,
    props: true,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
