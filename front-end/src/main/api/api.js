import axios from "axios/index";

export let post = (url, data) => {
    return axios.post(url, data)
        .then((response) => {
            return response;
        })
        .catch((err) => {
            return err;
        })
};