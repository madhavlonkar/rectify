document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('filterForm');

    form.addEventListener('submit', function (event) {
        let valid = true;
        let message = '';

        const minArea = document.getElementById('minArea').value.trim();
        const maxArea = document.getElementById('maxArea').value.trim();
        const minBedrooms = document.getElementById('minBedrooms').value.trim();
        const maxBedrooms = document.getElementById('maxBedrooms').value.trim();

        if (minArea && maxArea && parseInt(minArea) > parseInt(maxArea)) {
            valid = false;
            message = 'Min Area should not be greater than Max Area.';
        } else if (minBedrooms && maxBedrooms && parseInt(minBedrooms) > parseInt(maxBedrooms)) {
            valid = false;
            message = 'Min Bedrooms should not be greater than Max Bedrooms.';
        }

        if (!valid) {
            event.preventDefault();
            alert(message);
        }
    });
});
