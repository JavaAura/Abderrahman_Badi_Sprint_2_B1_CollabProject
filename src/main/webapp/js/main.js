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
  ev.target.appendChild(document.getElementById(id));

  // Find the closest drop zone (the parent container)
  let dropZone = ev.target;

  if (!dropZone.classList.contains("drop-zone")) {
    const bounding = ev.target.getBoundingClientRect();
    const offset = ev.clientY - bounding.top;

    if (offset < bounding.height / 2) {
      dropZone.insertBefore(draggedElement, ev.target);
    } else {
      ev.target.insertAdjacentElement("afterend", draggedElement);
    }
  } else {
    dropZone.appendChild(draggedElement);
  }
}

window.addEventListener("DOMContentLoaded", () => {
  // Get the element by id
  const elements = document.getElementsByClassName("card");

  Array.from(elements).forEach((element) => {
    // Make the element draggable
    element.setAttribute("draggable", "true");

    element.addEventListener("dragstart", dragstartHandler);
  });
});
