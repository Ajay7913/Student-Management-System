import React, { Fragment } from "react";
import axios from "axios";
import base_url from "../../Bootapi";
import { useState } from "react";
import { Input } from "reactstrap";
import { Button } from "reactstrap";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";

const StudentMarksAttendanceCompoForAdd = (props) => {
  var x;

  const [smaDto, setSmaDto] = useState({});
  console.log("transientSmaDto", props.transientSmaDto);

  const formHandler = (event) => {
    event.preventDefault();
    const data = {
      subjectId: props.transientSmaDto.subjectId,
      courseId: props.transientSmaDto.courseId,
      studentId: props.transientSmaDto.studentId,
      marks: smaDto.marks,
      attendance: smaDto.attendance,
      subjectName: props.transientSmaDto.subjectName,
    };
    console.log("data:", data);
    addPostToServer(data);
  };

  const addPostToServer = (data) => {
    axios.post(`${base_url}/admin/add_attendance_marks`, data).then(
      (response) => {
        //response from server
        console.log(response.data);
        if (response.data === "SMA Found cant insert try update")
          toast.warning("SMA Found cant insert try update");
        else if (response.data === "SMA Record inserted")
          toast.success("Inserted Successfully");
      },
      (error) => {
        //error handling
        console.log(error);
        toast.error("Something went Wrong");
      }
    );
  };

  return (
    <div>
      <table className="table">
        <thead>
          <tr>
            <th>Subject Id</th>
            <th>Subject Name</th>
            <th>Subject Marks</th>
            <th>Subject Attendance</th>
          </tr>
        </thead>

        <tbody>
          <tr>
            <td>
              <Input readonly value={props.transientSmaDto.subjectId}></Input>
            </td>
            <td>
              <Input readonly value={props.transientSmaDto.subjectName}></Input>
            </td>
            <td>
              <Input
                placeholder="Marks"
                onChange={(e) => {
                  setSmaDto({ ...smaDto, marks: e.target.value });
                }}
              ></Input>
            </td>
            <td>
              <Input
                placeholder="attendance"
                onChange={(e) => {
                  setSmaDto({ ...smaDto, attendance: e.target.value });
                }}
              ></Input>
            </td>
            <td>
              <Button
                type="submit"
                color="success"
                outline
                onClick={formHandler}
              >
                Add
              </Button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default StudentMarksAttendanceCompoForAdd;
