const baseURL = 'http://localhost:8000'

export const fetchData = async (url) => {
    const res = await fetch(baseURL + url, {credentials: "include",})
    try {
        console.log(res)
        return res.json()
    } catch (e) {
    }
}
