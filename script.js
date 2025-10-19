const canvas = document.getElementById("heart");
const ctx = canvas.getContext("2d");
let width = canvas.width = window.innerWidth;
let height = canvas.height = window.innerHeight;

window.onresize = () => {
  width = canvas.width = window.innerWidth;
  height = canvas.height = window.innerHeight;
};

const particles = [];
const particleCount = 8000; // nhiều đốm sáng hơn

function heartFunction(t) {
  const x = 16 * Math.pow(Math.sin(t), 3);
  const y = 13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t);
  return { x, y };
}

for (let i = 0; i < particleCount; i++) {
  const t = Math.random() * Math.PI * 2;
  const { x, y } = heartFunction(t);
  particles.push({
    x: Math.random() * width,
    y: Math.random() * height,
    targetX: width / 2 + x * 20,
    targetY: height / 2 - y * 20,
    size: Math.random() * 1 + 0.2, // nhỏ hơn
    speed: Math.random() * 0.02 + 0.001, // chậm hơn
    alpha: 0
  });
}

function animate() {
  ctx.clearRect(0, 0, width, height);
  ctx.globalCompositeOperation = "lighter";

  particles.forEach(p => {
    p.x += (p.targetX - p.x) * p.speed;
    p.y += (p.targetY - p.y) * p.speed;
    p.alpha += (1 - p.alpha) * 0.05;

    ctx.beginPath();
    const gradient = ctx.createRadialGradient(p.x, p.y, 0, p.x, p.y, 8);
    gradient.addColorStop(0, `rgba(200, 120, 255, ${p.alpha})`);
    gradient.addColorStop(1, `rgba(80, 0, 180, 0)`);
    ctx.fillStyle = gradient;
    ctx.arc(p.x, p.y, p.size, 0, Math.PI * 2);
    ctx.fill();
  });

  requestAnimationFrame(animate);
}

animate();

// Hiện chữ thật đẹp sau 3 giây
setTimeout(() => {
  document.getElementById("text").classList.add("show");
}, 8000);
