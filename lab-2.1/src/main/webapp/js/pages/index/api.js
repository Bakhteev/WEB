const baseURL = 'http://localhost:8000'

export const fetchData = async (url) => {
    const res = await fetch(baseURL + url, {credentials: "include",})
    return res.json()
}
