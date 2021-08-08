gsap.registerPlugin(ScrollTrigger);

gsap.to(".box2", {
  scrollTrigger: {
    trigger: ".box2",
    start: "0px 80%",
    markers: true,
  },
  x: 150,
  y: 250,
  duration: 1.2,
});

gsap.to(".box3", {
  scrollTrigger: {
    trigger: ".box3",
    start: "0px 80%",
    markers: true,
  },
  x: 300,
  y: 500,
  duration: 1.2,
});

// let t1 = gsap.timeline();

// t1.fromTo(".box2", 0.7, { x: "0", y: "0" }, { x: "150", y: "250" });
// t1.fromTo(".box3", 0.7, { x: "0", y: "0" }, { x: "300", y: "500" });
