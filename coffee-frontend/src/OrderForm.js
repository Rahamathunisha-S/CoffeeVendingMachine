import React, { useState } from 'react';

const options = {
  Milk: ['Chocolate', 'Strawberry', 'Plain'],
  Tea: ['Regular', 'Masala', 'Lemon', 'Green', 'Tulsi', 'Cardamom'],
  Coffee: ['Espresso', 'Latte', 'Cappuccino', 'Mocha', 'Black'],
  Others: ['Horlicks', 'Complan', 'Boost', 'Bournvita'],
};

function OrderForm({ setOrder }) {
  const [type, setType] = useState('Milk');
  const [flavor, setFlavor] = useState('');
  const [temperature, setTemperature] = useState('Hot');
  const [wantSweetener, setWantSweetener] = useState(false);
  const [sweetener, setSweetener] = useState('Sugar');
  const [lactoseFree, setLactoseFree] = useState(false);

  const generateOrderId = () => type.slice(0, 3).toUpperCase() + Date.now();

  const calculateCost = () => {
    let baseCost = {
      Milk: 30,
      Tea: 20,
      Coffee: 40,
      Others: 35,
    }[type];

    if (type !== 'Milk' && flavor !== options[type][0]) baseCost += 10;
    if (type === 'Milk' && flavor !== 'Plain') baseCost += 10;
    if (type === 'Coffee' && flavor !== 'Black') baseCost += 10;
    if (lactoseFree) baseCost += 5;

    return baseCost;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
      if (flavor === "") {
    alert("❗ Please select a flavor.");
    return;
  }

    const now = new Date();
    const date = now.toISOString().split("T")[0];
    const time = now.toTimeString().split(" ")[0];

    const order = {
      orderId: generateOrderId(),
      drinkType: type,
      flavor,
      temperature,
      sweetener: wantSweetener ? sweetener : "None",
      lactoseFree,
      cost: calculateCost(),
      date,
      time,
    };

    console.log("🟡 Order object to send:", order);

    try {
      const response = await fetch('http://localhost:8080/api/saveOrder', 

{
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(order),
      });

      console.log("🔵 Server response status:", response.status);

      if (response.ok) {
        const text = await response.text(); // read the server's response
        console.log("✅ Server responded:", text);
        setOrder(order);
        alert("✅ Order submitted successfully!");
      } else {
        alert("❌ Failed to submit order.");
        console.error("Server error:", response.status);
      }
    } catch (err) {
      alert("❌ Error submitting order.");
      console.error("🔴 Fetch error:", err);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="order-form">
      <label>
        Drink Type:
        <select value={type} onChange={(e) => { setType(e.target.value); setFlavor(''); }}>
          {Object.keys(options).map((opt) => (
            <option key={opt}>{opt}</option>
          ))}
        </select>
      </label>

      <label>
        Flavor:
        <select value={flavor} onChange={(e) => setFlavor(e.target.value)}>
          <option value="">-- Choose Flavor --</option>
          {options[type].map((fl) => (
            <option key={fl}>{fl}</option>
          ))}
        </select>
      </label>

      <label>
        Temperature:
        <select value={temperature} onChange={(e) => setTemperature(e.target.value)}>
          <option>Hot</option>
          <option>Cold</option>
        </select>
      </label>

      <label>
        Want Sweetener?
        <input
          type="checkbox"
          checked={wantSweetener}
          onChange={(e) => setWantSweetener(e.target.checked)}
        />
      </label>

      {wantSweetener && (
        <label>
          Sweetener Type:
          <select value={sweetener} onChange={(e) => setSweetener(e.target.value)}>
            <option>Sugar</option>
            <option>Honey</option>
            <option>Jaggery</option>
            <option>Artificial</option>
          </select>
        </label>
      )}

      <label>
        Lactose-Free:
        <input
          type="checkbox"
          checked={lactoseFree}
          onChange={(e) => setLactoseFree(e.target.checked)}
        />
      </label>

      <button type="submit" className="submit-btn">Submit Order</button>
    </form>
  );
}

export default OrderForm;
