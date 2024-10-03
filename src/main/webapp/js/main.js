function dragstartHandler(ev) {
  // Add the target element's id to the data transfer object
  ev.dataTransfer.setData("text/plain", ev.target.id);

  ev.dataTransfer.dropEffect = "move";

  ev.dataTransfer.setDragImage(ev.target, 0, 0);
}

function dragoverHandler(ev) {
  ev.preventDefault();
  ev.dataTransfer.dropEffect = "move";
}

function dropHandler(ev) {
  ev.preventDefault();

  const id = ev.dataTransfer.getData("text/plain");
  const draggedElement = document.getElementById(id);

  const dropZone = ev.target.closest(".drop-zone");
  
  const targetCard = ev.target.closest(".card");

  console.log("target Card:" + targetCard)

  if (targetCard) {

    const bounding = targetCard.getBoundingClientRect();

    // Mouse Y position (px) - Top border to top view port distance (px)
    const offset = ev.clientY - bounding.top;

    // If the offset is longer than half the height of the card the mouse was on the bottom half of the card

    if (offset < bounding.height / 2) {
      // Insert before the element
      dropZone.insertBefore(draggedElement, targetCard);
    } else {
      // Insert after the element
      targetCard.insertAdjacentElement("afterend", draggedElement);
    }
  } else {
    dropZone.appendChild(draggedElement);
  }
}

window.addEventListener("DOMContentLoaded", () => {

  const elements = document.getElementsByClassName("card");

  Array.from(elements).forEach((element) => {
    // Make the element draggable
    element.setAttribute("draggable", "true");

    element.addEventListener("dragstart", dragstartHandler);
  });
});
