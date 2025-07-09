import React from 'react';
import './App.css';

function OrderSummary({ order, onReset }) {
  return (
    <div className="order-summary">
      <h2>✅ Order Summary</h2>
      <p><strong>Order ID:</strong> {order.orderId}</p>
      <p><strong>Drink Type:</strong> {order.drinkType}</p>
      <p><strong>Flavor:</strong> {order.flavor}</p>
      <p><strong>Temperature:</strong> {order.temperature}</p>
      <p><strong>Sweetener:</strong> {order.sweetener}</p>
      <p><strong>Lactose-Free:</strong> {order.lactoseFree ? "Yes" : "No"}</p>
      <p><strong>Cost:</strong> ₹{order.cost}</p>
      <p><strong>Date:</strong> {order.date}</p>
      <p><strong>Time:</strong> {order.time}</p>

      <button className="submit-btn" onClick={onReset}>
        🧾 Place Another Order
      </button>
    </div>
  );
}

export default OrderSummary;
