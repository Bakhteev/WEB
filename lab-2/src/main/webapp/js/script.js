const form = document.querySelector('#form')

const sendData = async (x, y, r) => {
    const formData = new FormData()
    formData.set("x", x)
    formData.set("y", y)
    formData.set("r", r)
    // formData.set("command", "hit")

    await fetch(`/`, {
        method: "GET",
        headers: {
            "Content-Type": "multipart/form-data"
        },
        body: formData,
    })
}

form.addEventListener('submit', (e) => {
    // e.preventDefault()
    const x = +form.querySelector("#x").value
    const y = +form.querySelector("#y").value
    const r = +form.querySelector("#r").value
    console.log(x, y, r)
    sendData(x, y, r).then(r => console.log(r))
})
