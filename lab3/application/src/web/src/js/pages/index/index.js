import "../../common/shoError.js"

const rSelect = document.querySelectorAll('#form\\:rValues input')

const yInput = document.querySelector("#form\\:y-input");
const xInput = document.querySelector("#form\\:x-value");
let graph = document.querySelector("#graph");
const dottedLines = document.querySelectorAll(".dotted-line");
let yLine = document.querySelector("#y-line");
let xLine = document.querySelector("#x-line");
let rValue;

const limit = {
    xMax: 5,
    xMin: -5,
    yMax: 5,
    yMin: -5,
    rMax: 5,
    rMin: 1,
}

document.addEventListener("DOMContentLoaded", () => {
    rSelect.forEach(el => {
        if (el.checked) {
            rValue = +el.value;
        }
    })
})

// rSelect.forEach(el => {
//     el.addEventListener('change', (e) => {
//         rSelect.forEach(item => {
//             if(item.checked) rValue = +item.value
//         })
//         // if (e.target.checked) {
//         //     console.log(true)
//         //     rValue = +e.target.value;
//         // }
//     })
// })

graph.addEventListener("mousemove", graphMouseMove);

function graphMouseMove(e) {
    const coord = e.offsetY - 20 * (e.offsetY / 320);
    yLine.setAttribute("stroke", "red");
    if (rValue) {
        const highLimit = limit.yMax * 100 / rValue;
        const lowLimit = -(limit.yMin * 100 / rValue);
        if (coord > 150) {
            yLine.setAttribute("y1", coord <= 150 + lowLimit ? coord : 150 + lowLimit);
            yLine.setAttribute("y2", coord <= 150 + lowLimit ? coord : 150 + lowLimit);
        } else {
            yLine.setAttribute("y1", coord >= 150 - highLimit ? coord : 150 - highLimit);
            yLine.setAttribute("y2", coord >= 150 - highLimit ? coord : 150 - highLimit);
        }
    } else {
        yLine.setAttribute("y1", coord);
        yLine.setAttribute("y2", coord);
    }

    const coordX = e.offsetX - 20 * (e.offsetX / 320);
    xLine.setAttribute("stroke", "red");
    if (rValue) {
        const highLimit = limit.xMax * 100 / rValue;
        const lowLimit = -(limit.xMin * 100 / rValue);
        if (coordX > 150) {
            xLine.setAttribute("x1", coordX <= 150 + highLimit ? coordX : 150 + highLimit);
            xLine.setAttribute("x2", coordX <= 150 + highLimit ? coordX : 150 + highLimit);
        } else {
            xLine.setAttribute("x1", coordX >= 150 - lowLimit ? coordX : 150 - lowLimit);
            xLine.setAttribute("x2", coordX >= 150 - lowLimit ? coordX : 150 - lowLimit);
        }
    } else {
        xLine.setAttribute("x1", coordX);
        xLine.setAttribute("x2", coordX);
    }
}

graph.addEventListener("mouseenter", graphMouseEnter);

function graphMouseEnter(e) {
    rSelect.forEach(el => {
        if (el.checked) {
            rValue = +el.value;
        }
    })
    yLine = document.querySelector("#y-line");
    xLine = document.querySelector("#x-line");
    changeRText(rValue)
}



graph.addEventListener("click", graphClick);

function graphClick(e){
    setDot(xLine.getAttribute("x1"), yLine.getAttribute("y1"))
    const x = +(((xLine.getAttribute("x1") - 150) / 100) * +rValue).toFixed(1);
    const y = +(-((yLine.getAttribute("y1") - 150) / 100) * +rValue).toFixed(1);
    xInput.value = x
    yInput.value = y
}

graph.addEventListener("mouseleave", () => {
    yLine.setAttribute("stroke", "transparent");
    xLine.setAttribute("stroke", "transparent");
    changeRText("R");
});


function changeRText(rValue) {
    const rlablesWhole = document.querySelectorAll(".graph-label.r-whole-pos");
    const rlablesHalf = document.querySelectorAll(".graph-label.r-half-pos");
    const rlablesNegWhole = document.querySelectorAll(".graph-label.r-whole-neg");
    const rlablesNegHalf = document.querySelectorAll(".graph-label.r-half-neg");
    rlablesWhole.forEach((el) => (el.textContent = +rValue ? rValue : "R"));
    rlablesHalf.forEach(
        (el) => (el.textContent = +rValue / 2 ? rValue / 2 : "R/2")
    );
    rlablesNegWhole.forEach((el) => (el.textContent = -rValue ? -rValue : "-R"));
    rlablesNegHalf.forEach(
        (el) => (el.textContent = -(rValue / 2) ? -(rValue / 2) : "-R/2")
    );
}

function setDot(x, y) {
    const circle = document.querySelector('#graph .dot')
    circle.setAttribute('cx', x)
    circle.setAttribute('cy', y)
    circle.classList.remove('inactive')
}

// document.querySelector("#form").addEventListener("submit", () =>{
//     circle.setAttribute('cx', -30)
//     circle.setAttribute('cy', -30)
// })