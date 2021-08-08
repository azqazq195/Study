new fullpage('#fullpage', {
    autoScrolling: true,
    navigation: true,
    onLeave: (origin, destination, direction) => {
        const section = destination.item;
        const title = section.querySelector('h1');
        const t1 = new TimelineMax({delay: 0.5});
        t1.fromTo(title, 0.5, {y: "50", opacity: 0}, {y: 0, opacity: 1})

        if(destination.index === 1) {
            const imgs = document.querySelectorAll('.img')
            const description = document.querySelectorAll('.description')

            t1.fromTo(imgs, 0.7, {x: "100%"}, {x: "-30%"})
            .fromTo(description[0], {opacity: 0}, {opacity: 1})
            .fromTo(description[1], {opacity: 0}, {opacity: 1})
            .fromTo(imgs[0], 1, {opacity: 0}, {opacity: 1})
            .fromTo(imgs[1], 1, {opacity: 0}, {opacity: 1})
            .fromTo(imgs[2], 1, {opacity: 0}, {opacity: 1})
        }
    }
});