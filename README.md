# FastTrackPOS

Java desktop point-of-sale and parcel tracking app built with Apache NetBeans and Swing. The main class is `fasttrackpos.FastTrackPOS`, which opens the login window.

This is not a Node.js project. Do not use `npm run dev`.

## Requirements

- JDK 8 or newer (the project source level is Java 7)
- Apache NetBeans
- MySQL, with a local database named `fasttrack`
- MySQL user `root` with an empty password, or update `src/Utils/Database/DatabaseConnection.java`
- These libraries added in NetBeans under Project Properties, Libraries:

  - `mysql-connector-java-5.1.49.jar`
  - `itextpdf-5.5.9.jar`
  - `jakarta.mail-1.6.3.jar`
  - `jcalendar-1.4.jar`
  - `jdatepicker-1.3.4.jar`
  - EclipseLink (already referenced by the NetBeans project)

The original library paths point at another computer. After you download the jars, set each path on your machine.

## Database tables the code expects

- `users` (`email`, `password`, `role`, `name`)
- `warehouses` (`user_id`, `address`, `district`)
- `vehicles` (`vehicle_type`, `registration_number`, `model`)
- `customers` (`user_id`, `address`, `telephone`, `closest_warehouse`)
- `agents` (`user_id`, `warehouse_id`, `vehicle_id`, `nic`, `telephone`, `status`)
- `shipments` (`delivery_agent_id`, `pickup_agent_id`, `delivery_warehouse_id`, `pickup_warehouse_id`, `customer_id`, `package_content`, `pickup_time`, `delivery_time`, `status`, `price`, `created_at`, `updated_at`, `address`)

## How to run

1. Open Apache NetBeans.
2. File, Open Project, and select this folder.
3. Fix the library paths if NetBeans reports missing jars.
4. Right-click the project and choose Run.

## Email

Outgoing mail uses Gmail SMTP. Set the app password in an environment variable before starting NetBeans. Do not put the password back into the source file.

```
FASTTRACK_MAIL_PASSWORD=your-gmail-app-password
```
