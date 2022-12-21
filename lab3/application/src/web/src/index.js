import './index.scss'
import './js/pages/index'
import {clearCookies} from "./js/pages/index/clearCookies.js";

document.querySelector("#logout").addEventListener("click", () => {
    clearCookies()
    document.location.reload();
})