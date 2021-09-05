function colorChannelMixer(colorChannelA: number, colorChannelB: number, amountToMix: number) {
    var channelA = colorChannelA * amountToMix;
    var channelB = colorChannelB * (1 - amountToMix);
    return channelA + channelB;
}

function colorMixer(rgbA: [number, number, number], rgbB: [number, number, number], amountToMix: number) {
    var r: number = colorChannelMixer(rgbA[0], rgbB[0], amountToMix);
    var g: number = colorChannelMixer(rgbA[1], rgbB[1], amountToMix);
    var b: number = colorChannelMixer(rgbA[2], rgbB[2], amountToMix);
    return "rgb(" + r + "," + g + "," + b + ")";
}

export default colorMixer