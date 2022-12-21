const baseURL = 'http://127.0.0.1:8000'

export const fetchData = async (url) => {
    const res = await fetch(baseURL + url, {credentials: "include",})
    return res.json()
}
