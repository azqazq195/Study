new fullpage('#fullpage', {
    autoScrolling: true,
    navigation: true,

    // onLeave: (origin, destination, direction) => {
    //     const section = destination.item;
    //     const title = section.querySelector('h1');
    //     const t1 = new TimelineMax({delay: 0.5});
    //     t1.fromTo(title, 0.7, {y: "50", opacity: 0}, {y: 0, opacity: 1})

    //     if(destination.index === 1) {
    //         const imgs = document.querySelectorAll('.img')

    //         // t1
    //         // .fromTo(imgs[0], 0.7, {x: "100%"}, {x: "0"})
    //         // .fromTo(imgs[1], 0.7, {x: "100%"}, {x: "-30"})
    //         // .fromTo(imgs[2], 0.7, {x: "100%"}, {x: "-60"})

    //         t1
    //         .fromTo(imgs[0], 0.7, {x: "0%"}, {x: "0"})
    //         .fromTo(imgs[1], 0.7, {x: "-100%"}, {x: "-30%", y: "75%"})
    //         .fromTo(imgs[2], 0.7, {x: "-200%"}, {x: "-60%", y: "150%"})
    //     }

    // }
});