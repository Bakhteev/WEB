export const coordConverter = (
  coord: number,
  rValue: number,
  neg: boolean = true
): number => {
  if (neg) {
    return +(((coord - 150) / 100) * rValue).toFixed(1)
  } else {
    return -(((coord - 150) / 100) * rValue).toFixed(1)
  }
}
