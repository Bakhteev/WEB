const d=document.querySelectorAll("#form\\:rValues input"),f=document.querySelector("#form\\:y-input"),x=document.querySelector("#form\\:x-value");let a=document.querySelector("#graph");document.querySelectorAll(".dotted-line");let c=document.querySelector("#y-line"),i=document.querySelector("#x-line"),s;const u={xMax:5,xMin:-5,yMax:5,yMin:-5,rMax:5,rMin:1};document.addEventListener("DOMContentLoaded",()=>{d.forEach(t=>{t.checked&&(s=+t.value)})});a.addEventListener("mousemove",b);function b(t){const e=t.offsetY-20*(t.offsetY/320);if(c.setAttribute("stroke","red"),s){const r=u.yMax*100/s,n=-(u.yMin*100/s);e>150?(c.setAttribute("y1",e<=150+n?e:150+n),c.setAttribute("y2",e<=150+n?e:150+n)):(c.setAttribute("y1",e>=150-r?e:150-r),c.setAttribute("y2",e>=150-r?e:150-r))}else c.setAttribute("y1",e),c.setAttribute("y2",e);const o=t.offsetX-20*(t.offsetX/320);if(i.setAttribute("stroke","red"),s){const r=u.xMax*100/s,n=-(u.xMin*100/s);o>150?(i.setAttribute("x1",o<=150+r?o:150+r),i.setAttribute("x2",o<=150+r?o:150+r)):(i.setAttribute("x1",o>=150-n?o:150-n),i.setAttribute("x2",o>=150-n?o:150-n))}else i.setAttribute("x1",o),i.setAttribute("x2",o)}a.addEventListener("mouseenter",h);function h(t){d.forEach(e=>{e.checked&&(s=+e.value)}),c=document.querySelector("#y-line"),i=document.querySelector("#x-line"),y(s)}a.addEventListener("click",m);function m(t){A(i.getAttribute("x1"),c.getAttribute("y1"));const e=+((i.getAttribute("x1")-150)/100*+s).toFixed(1),o=+(-((c.getAttribute("y1")-150)/100)*+s).toFixed(1);x.value=e,f.value=o}a.addEventListener("mouseleave",()=>{c.setAttribute("stroke","transparent"),i.setAttribute("stroke","transparent"),y("R")});function y(t){const e=document.querySelectorAll(".graph-label.r-whole-pos"),o=document.querySelectorAll(".graph-label.r-half-pos"),r=document.querySelectorAll(".graph-label.r-whole-neg"),n=document.querySelectorAll(".graph-label.r-half-neg");e.forEach(l=>l.textContent=+t?t:"R"),o.forEach(l=>l.textContent=+t/2?t/2:"R/2"),r.forEach(l=>l.textContent=-t?-t:"-R"),n.forEach(l=>l.textContent=-(t/2)?-(t/2):"-R/2")}function A(t,e){const o=document.querySelector("#graph .dot");o.setAttribute("cx",t),o.setAttribute("cy",e),o.classList.remove("inactive")}
