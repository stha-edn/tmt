// When plain htmx isn't quite enough, you can stick some custom JS here.

// "Get Started" cursor-arrow CTA effect, adapted from
// https://codepen.io/TWilson/pen/raaGNGa. Scoped to #cta-arrow-region so the
// arrow only appears while hovering that component, not the whole page.
// No-ops on pages that don't have all three of #cta-arrow-region,
// #cta-arrow-canvas and #cta-arrow-target.
document.addEventListener("DOMContentLoaded", () => {
  const region = document.getElementById("cta-arrow-region");
  const canvas = document.getElementById("cta-arrow-canvas");
  const target = document.getElementById("cta-arrow-target");
  if (!region || !canvas || !target) return;

  const ctx = canvas.getContext("2d");

  const updateCanvasSize = () => {
    canvas.width = region.clientWidth;
    canvas.height = region.clientHeight;
  };
  window.addEventListener("resize", updateCanvasSize);
  updateCanvasSize();

  const mouse = { x: null, y: null };
  region.addEventListener("mousemove", (e) => {
    const regionRect = region.getBoundingClientRect();
    mouse.x = e.clientX - regionRect.left;
    mouse.y = e.clientY - regionRect.top;
  });
  region.addEventListener("mouseleave", () => {
    mouse.x = null;
    mouse.y = null;
  });

  const drawArrow = () => {
    const x0 = mouse.x;
    const y0 = mouse.y;
    if (!x0 || !y0) return;

    const regionRect = region.getBoundingClientRect();
    const rect = target.getBoundingClientRect();
    const cx = rect.left + rect.width / 2 - regionRect.left;
    const cy = rect.top + rect.height / 2 - regionRect.top;

    const a = Math.atan2(cy - y0, cx - x0);
    const x1 = cx - Math.cos(a) * (rect.width / 2 + 12);
    const y1 = cy - Math.sin(a) * (rect.height / 2 + 12);

    const midX = (x0 + x1) / 2;
    const midY = (y0 + y1) / 2;
    const offset = Math.min(200, Math.hypot(x1 - x0, y1 - y0) * 0.5);
    const t = Math.max(-1, Math.min(1, (y0 - y1) / 200));
    const controlX = midX;
    const controlY = midY + offset * t;

    const r = Math.sqrt((x1 - x0) ** 2 + (y1 - y0) ** 2);
    // Clamp (rather than just cap) so opacity can't go negative near the
    // target, which produced an invalid rgba() and made the stroke vanish.
    const opacity = Math.max(0.45, Math.min(0.95, (r - Math.max(rect.width, rect.height) / 2) / 500));

    const angle = Math.atan2(y1 - controlY, x1 - controlX);
    const headLength = 14;
    const tracePath = () => {
      ctx.beginPath();
      ctx.moveTo(x0, y0);
      ctx.quadraticCurveTo(controlX, controlY, x1, y1);
      ctx.stroke();
    };
    const traceHead = () => {
      ctx.beginPath();
      ctx.moveTo(x1, y1);
      ctx.lineTo(
        x1 - headLength * Math.cos(angle - Math.PI / 6),
        y1 - headLength * Math.sin(angle - Math.PI / 6)
      );
      ctx.moveTo(x1, y1);
      ctx.lineTo(
        x1 - headLength * Math.cos(angle + Math.PI / 6),
        y1 - headLength * Math.sin(angle + Math.PI / 6)
      );
      ctx.stroke();
    };

    ctx.lineCap = "round";
    ctx.lineJoin = "round";

    // Dark halo pass first so the line reads against light backgrounds...
    ctx.save();
    ctx.strokeStyle = `rgba(15,23,42,${opacity * 0.8})`;
    ctx.lineWidth = 4;
    ctx.setLineDash([10, 4]);
    tracePath();
    ctx.restore();
    ctx.strokeStyle = `rgba(15,23,42,${opacity * 0.8})`;
    ctx.lineWidth = 4;
    traceHead();

    // ...then a bright core on top so it also reads against dark backgrounds.
    ctx.save();
    ctx.strokeStyle = `rgba(255,255,255,${opacity})`;
    ctx.lineWidth = 2;
    ctx.setLineDash([10, 4]);
    tracePath();
    ctx.restore();
    ctx.strokeStyle = `rgba(255,255,255,${opacity})`;
    ctx.lineWidth = 2;
    traceHead();
  };

  const animate = () => {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawArrow();
    requestAnimationFrame(animate);
  };
  animate();
});
