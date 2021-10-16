const getMouse = (event: React.MouseEvent<SVGElement, MouseEvent>, width: number, height: number) => {
    const dims = event.currentTarget.getBoundingClientRect();
    const rawX = event.clientX - dims.left;
    const rawY = event.clientY - dims.top;
    const x = (rawX / dims.width) * width;
    const y = (rawY / dims.height) * height;
    return { x, y };
};

export default getMouse