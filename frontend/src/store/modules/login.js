// import axios from "axios";

const state = {
  userToken: null,
  username: "null",
};

const actions = {
  // getJWT: function(context, credentials) {
  //   axios({
  //     method: "post",
  //     url: `${SERVER.URL}/user/login`,
  //     headers: {
  //       "Access-Control-Allow-Origin": "*",
  //     },
  //     data: credentials,
  //   })
  //     .then((res) => {
  //       console.log(res.data.jwt)
  //       context.commit("saveJWT", res.data.jwt)
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // },
  // deleteJWT: function(context) {
  //   context.commit("deleteJWT");
  // },
};

const mutations = {
  // saveJWT: function(state, token) {
  //   state.userToken = token;
  //   console.log(state.userToken)
  // },
  // deleteJWT: function(state) {
  //   state.userToken = null;
  // },
};

const getters = {
  // decodedToken: function (state) {
  //   if (state.userToken) {
  //     console.log(state.userToken);
  //     return jwt_decode(state.userToken);
  //   } else {
  //     return null;
  //   }
  // },
};

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters,
};
