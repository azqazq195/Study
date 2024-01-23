import React, { useRef } from "react";
import useLocomotiveScroll from "./Scroll";

const ScrollCompoents: React.FC = () => {
  const scrollRef = useRef<HTMLDivElement>(null);
  useLocomotiveScroll(scrollRef);

  return (
    <div ref={scrollRef}>
      <main style={{ height: "150vh" }}>
        <div>
          <h1>Hello 👋</h1>
        </div>
        <div>
          <h2 data-scroll data-scroll-speed="0.5">
            What's up?
          </h2>
          <p data-scroll data-scroll-speed="0.8">
            😬
          </p>
        </div>
      </main>
    </div>
  );
};

export default ScrollCompoents;
